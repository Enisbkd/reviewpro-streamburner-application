/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RvpApiResponse from './rvp-api-response.vue';
import RvpApiResponseService from './rvp-api-response.service';
import AlertService from '@/shared/alert/alert.service';

type RvpApiResponseComponentType = InstanceType<typeof RvpApiResponse>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RvpApiResponse Management Component', () => {
    let rvpApiResponseServiceStub: SinonStubbedInstance<RvpApiResponseService>;
    let mountOptions: MountingOptions<RvpApiResponseComponentType>['global'];

    beforeEach(() => {
      rvpApiResponseServiceStub = sinon.createStubInstance<RvpApiResponseService>(RvpApiResponseService);
      rvpApiResponseServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          jhiItemCount: true,
          bPagination: true,
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'jhi-sort-indicator': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          rvpApiResponseService: () => rvpApiResponseServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        rvpApiResponseServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(RvpApiResponse, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(rvpApiResponseServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.rvpApiResponses[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(RvpApiResponse, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(rvpApiResponseServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: RvpApiResponseComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RvpApiResponse, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        rvpApiResponseServiceStub.retrieve.reset();
        rvpApiResponseServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        rvpApiResponseServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(rvpApiResponseServiceStub.retrieve.called).toBeTruthy();
        expect(comp.rvpApiResponses[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(rvpApiResponseServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        rvpApiResponseServiceStub.retrieve.reset();
        rvpApiResponseServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(rvpApiResponseServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.rvpApiResponses[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(rvpApiResponseServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        rvpApiResponseServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRvpApiResponse();
        await comp.$nextTick(); // clear components

        // THEN
        expect(rvpApiResponseServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(rvpApiResponseServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
