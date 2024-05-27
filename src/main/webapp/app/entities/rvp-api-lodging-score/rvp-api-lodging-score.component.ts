import { defineComponent, inject, onMounted, ref, type Ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';

import RvpApiLodgingScoreService from './rvp-api-lodging-score.service';
import { type IRvpApiLodgingScore } from '@/shared/model/rvp-api-lodging-score.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RvpApiLodgingScore',
  setup() {
    const { t: t$ } = useI18n();
    const rvpApiLodgingScoreService = inject('rvpApiLodgingScoreService', () => new RvpApiLodgingScoreService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const itemsPerPage = ref(20);
    const queryCount: Ref<number> = ref(null);
    const page: Ref<number> = ref(1);
    const propOrder = ref('id');
    const reverse = ref(false);
    const totalItems = ref(0);

    const rvpApiLodgingScores: Ref<IRvpApiLodgingScore[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {
      page.value = 1;
    };

    const sort = (): Array<any> => {
      const result = [propOrder.value + ',' + (reverse.value ? 'desc' : 'asc')];
      if (propOrder.value !== 'id') {
        result.push('id');
      }
      return result;
    };

    const retrieveRvpApiLodgingScores = async () => {
      isFetching.value = true;
      try {
        const paginationQuery = {
          page: page.value - 1,
          size: itemsPerPage.value,
          sort: sort(),
        };
        const res = await rvpApiLodgingScoreService().retrieve(paginationQuery);
        totalItems.value = Number(res.headers['x-total-count']);
        queryCount.value = totalItems.value;
        rvpApiLodgingScores.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRvpApiLodgingScores();
    };

    onMounted(async () => {
      await retrieveRvpApiLodgingScores();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRvpApiLodgingScore) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRvpApiLodgingScore = async () => {
      try {
        await rvpApiLodgingScoreService().delete(removeId.value);
        const message = t$('reviewProStreamBurnerApplicationApp.rvpApiLodgingScore.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRvpApiLodgingScores();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    const changeOrder = (newOrder: string) => {
      if (propOrder.value === newOrder) {
        reverse.value = !reverse.value;
      } else {
        reverse.value = false;
      }
      propOrder.value = newOrder;
    };

    // Whenever order changes, reset the pagination
    watch([propOrder, reverse], async () => {
      if (page.value === 1) {
        // first page, retrieve new data
        await retrieveRvpApiLodgingScores();
      } else {
        // reset the pagination
        clear();
      }
    });

    // Whenever page changes, switch to the new page.
    watch(page, async () => {
      await retrieveRvpApiLodgingScores();
    });

    return {
      rvpApiLodgingScores,
      handleSyncList,
      isFetching,
      retrieveRvpApiLodgingScores,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRvpApiLodgingScore,
      itemsPerPage,
      queryCount,
      page,
      propOrder,
      reverse,
      totalItems,
      changeOrder,
      t$,
    };
  },
});
