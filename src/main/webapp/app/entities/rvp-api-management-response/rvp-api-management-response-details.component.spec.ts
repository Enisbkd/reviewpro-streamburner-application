/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RvpApiManagementResponseDetails from './rvp-api-management-response-details.vue';
import RvpApiManagementResponseService from './rvp-api-management-response.service';
import AlertService from '@/shared/alert/alert.service';

type RvpApiManagementResponseDetailsComponentType = InstanceType<typeof RvpApiManagementResponseDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const rvpApiManagementResponseSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RvpApiManagementResponse Management Detail Component', () => {
    let rvpApiManagementResponseServiceStub: SinonStubbedInstance<RvpApiManagementResponseService>;
    let mountOptions: MountingOptions<RvpApiManagementResponseDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      rvpApiManagementResponseServiceStub = sinon.createStubInstance<RvpApiManagementResponseService>(RvpApiManagementResponseService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          rvpApiManagementResponseService: () => rvpApiManagementResponseServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        rvpApiManagementResponseServiceStub.find.resolves(rvpApiManagementResponseSample);
        route = {
          params: {
            rvpApiManagementResponseId: '' + 123,
          },
        };
        const wrapper = shallowMount(RvpApiManagementResponseDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.rvpApiManagementResponse).toMatchObject(rvpApiManagementResponseSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        rvpApiManagementResponseServiceStub.find.resolves(rvpApiManagementResponseSample);
        const wrapper = shallowMount(RvpApiManagementResponseDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
