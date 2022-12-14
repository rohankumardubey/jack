package com.rapleaf.jack.store.executors;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rapleaf.jack.exception.SqlExecutionFailureException;
import com.rapleaf.jack.store.exceptions.InvalidRecordException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestInternalScopeGetter extends BaseExecutorTestCase {
  private static final Logger LOG = LoggerFactory.getLogger(TestInternalScopeGetter.class);

  @Test
  public void testGetAllSubScopeIds() throws Exception {
    // empty parent record
    long parentScopeId = createSubScope(Optional.empty(), Optional.empty());
    assertTrue(transactor.queryAsTransaction(db -> InternalScopeGetter.getAllSubRecordIds(db, table, parentScopeId)).isEmpty());

    // parent record with sub records
    Set<Long> subScopeIds = Sets.newHashSet();
    int size = Math.max(3, RANDOM.nextInt(5));
    transactor.executeAsTransaction(db -> {
      for (int i = 0; i < size; ++i) {
        subScopeIds.add(jackStore.record(parentScopeId).createSubRecord().execute(db).getRecordId());
      }
    });
    assertEquals(subScopeIds, transactor.queryAsTransaction(db -> InternalScopeGetter.getAllSubRecordIds(db, table, parentScopeId)));
  }

  @Test
  public void testGetValidSubScopeIds() throws Exception {
    long parentScopeId = createSubScope(Optional.empty(), Optional.empty());
    int size = 20;
    List<Long> subScopeIds = Lists.newArrayListWithCapacity(20);
    for (int i = 0; i < size; ++i) {
      subScopeIds.add(createSubScope(Optional.of(parentScopeId), Optional.empty()));
    }

    // all sub record IDs are valid
    Random random = new Random(System.currentTimeMillis());
    int lo = Math.max(1, random.nextInt(size / 2));
    int hi = Math.min(size, lo + size / 5 + random.nextInt(size));
    LOG.info("Range: [{}, {})", lo, hi);
    List<Long> selectedSubScopeIds = subScopeIds.subList(lo, hi);
    Set<Long> expectedSubScopeIds1 = Sets.newHashSet(selectedSubScopeIds);
    transactor.executeAsTransaction(db -> InternalScopeGetter.validateSubRecordIds(db, table, parentScopeId, expectedSubScopeIds1));

    // throw exception when there is any invalid sub record ID
    long maxScopeId = Collections.max(selectedSubScopeIds);
    try {
      transactor.executeAsTransaction(db -> InternalScopeGetter.validateSubRecordIds(db, table, parentScopeId, Sets.newHashSet(maxScopeId, maxScopeId + 100L, maxScopeId + 200L)));
      fail();
    } catch (SqlExecutionFailureException e) {
      assertTrue(e.getCause() instanceof InvalidRecordException);
    }
  }

  @Test
  public void testGetNestedScopeIds() throws Exception {
    /*
     * root ????????? s1 ????????? s11 ????????? s111 ????????? s1111
     *       ???      ???       ?????? s112
     *       ???      ?????? s12
     *       ?????? s2
     */
    long s1 = createSubScope(Optional.empty(), Optional.empty());
    long s2 = createSubScope(Optional.empty(), Optional.empty());
    long s11 = createSubScope(Optional.of(s1), Optional.empty());
    long s12 = createSubScope(Optional.of(s1), Optional.empty());
    long s111 = createSubScope(Optional.of(s11), Optional.empty());
    long s112 = createSubScope(Optional.of(s11), Optional.empty());
    long s1111 = createSubScope(Optional.of(s111), Optional.empty());

    transactor.executeAsTransaction(db -> {
      assertEquals(Sets.newHashSet(s11, s12, s111, s112, s1111), InternalScopeGetter.getNestedRecordIds(db, table, Collections.singleton(s1)));
      assertEquals(Sets.newHashSet(), InternalScopeGetter.getNestedRecordIds(db, table, Collections.singleton(s2)));
      assertEquals(Sets.newHashSet(s111, s112, s1111), InternalScopeGetter.getNestedRecordIds(db, table, Collections.singleton(s11)));
      assertEquals(Sets.newHashSet(s1111), InternalScopeGetter.getNestedRecordIds(db, table, Collections.singleton(s111)));
      assertEquals(Sets.newHashSet(), InternalScopeGetter.getNestedRecordIds(db, table, Collections.singleton(s1111)));
      assertEquals(Sets.newHashSet(), InternalScopeGetter.getNestedRecordIds(db, table, Collections.singleton(s112)));
      assertEquals(Sets.newHashSet(), InternalScopeGetter.getNestedRecordIds(db, table, Collections.singleton(s12)));
    });
  }

}
