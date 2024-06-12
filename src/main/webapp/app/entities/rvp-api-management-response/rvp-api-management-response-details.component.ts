import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RvpApiManagementResponseService from './rvp-api-management-response.service';
import { type IRvpApiManagementResponse } from '@/shared/model/rvp-api-management-response.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RvpApiManagementResponseDetails',
  setup() {
    const rvpApiManagementResponseService = inject('rvpApiManagementResponseService', () => new RvpApiManagementResponseService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const rvpApiManagementResponse: Ref<IRvpApiManagementResponse> = ref({});

    const retrieveRvpApiManagementResponse = async rvpApiManagementResponseId => {
      try {
        const res = await rvpApiManagementResponseService().find(rvpApiManagementResponseId);
        rvpApiManagementResponse.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.rvpApiManagementResponseId) {
      retrieveRvpApiManagementResponse(route.params.rvpApiManagementResponseId);
    }

    return {
      alertService,
      rvpApiManagementResponse,

      previousState,
      t$: useI18n().t,
    };
  },
});
