import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RvpApiManagementResponseService from './rvp-api-management-response.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IRvpApiManagementResponse, RvpApiManagementResponse } from '@/shared/model/rvp-api-management-response.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RvpApiManagementResponseUpdate',
  setup() {
    const rvpApiManagementResponseService = inject('rvpApiManagementResponseService', () => new RvpApiManagementResponseService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const rvpApiManagementResponse: Ref<IRvpApiManagementResponse> = ref(new RvpApiManagementResponse());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      source: {},
      lodgingId: {},
      fd: {},
      td: {},
      respondableCountsPositive: {},
      respondableCountsNegative: {},
      respondedCountsPositive: {},
      respondedCountsNegative: {},
    };
    const v$ = useVuelidate(validationRules, rvpApiManagementResponse as any);
    v$.value.$validate();

    return {
      rvpApiManagementResponseService,
      alertService,
      rvpApiManagementResponse,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.rvpApiManagementResponse.id) {
        this.rvpApiManagementResponseService()
          .update(this.rvpApiManagementResponse)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(
              this.t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.updated', { param: param.id }),
            );
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.rvpApiManagementResponseService()
          .create(this.rvpApiManagementResponse)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.created', { param: param.id }).toString(),
            );
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
