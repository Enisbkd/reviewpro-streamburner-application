/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RvpApiManagementResponseUpdate from './rvp-api-management-response-update.vue';
import RvpApiManagementResponseService from './rvp-api-management-response.service';
import AlertService from '@/shared/alert/alert.service';

type RvpApiManagementResponseUpdateComponentType = InstanceType<typeof RvpApiManagementResponseUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const rvpApiManagementResponseSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RvpApiManagementResponseUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RvpApiManagementResponse Management Update Component', () => {
    let comp: RvpApiManagementResponseUpdateComponentType;
    let rvpApiManagementResponseServiceStub: SinonStubbedInstance<RvpApiManagementResponseService>;

    beforeEach(() => {
      route = {};
      rvpApiManagementResponseServiceStub = sinon.createStubInstance<RvpApiManagementResponseService>(RvpApiManagementResponseService);
      rvpApiManagementResponseServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          rvpApiManagementResponseService: () => rvpApiManagementResponseServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(RvpApiManagementResponseUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.rvpApiManagementResponse = rvpApiManagementResponseSample;
        rvpApiManagementResponseServiceStub.update.resolves(rvpApiManagementResponseSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rvpApiManagementResponseServiceStub.update.calledWith(rvpApiManagementResponseSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        rvpApiManagementResponseServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RvpApiManagementResponseUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.rvpApiManagementResponse = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rvpApiManagementResponseServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        rvpApiManagementResponseServiceStub.find.resolves(rvpApiManagementResponseSample);
        rvpApiManagementResponseServiceStub.retrieve.resolves([rvpApiManagementResponseSample]);

        // WHEN
        route = {
          params: {
            rvpApiManagementResponseId: '' + rvpApiManagementResponseSample.id,
          },
        };
        const wrapper = shallowMount(RvpApiManagementResponseUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.rvpApiManagementResponse).toMatchObject(rvpApiManagementResponseSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        rvpApiManagementResponseServiceStub.find.resolves(rvpApiManagementResponseSample);
        const wrapper = shallowMount(RvpApiManagementResponseUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
