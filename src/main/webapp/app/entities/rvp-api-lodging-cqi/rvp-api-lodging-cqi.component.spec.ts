/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RvpApiLodgingCqi from './rvp-api-lodging-cqi.vue';
import RvpApiLodgingCqiService from './rvp-api-lodging-cqi.service';
import AlertService from '@/shared/alert/alert.service';

type RvpApiLodgingCqiComponentType = InstanceType<typeof RvpApiLodgingCqi>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RvpApiLodgingCqi Management Component', () => {
    let rvpApiLodgingCqiServiceStub: SinonStubbedInstance<RvpApiLodgingCqiService>;
    let mountOptions: MountingOptions<RvpApiLodgingCqiComponentType>['global'];

    beforeEach(() => {
      rvpApiLodgingCqiServiceStub = sinon.createStubInstance<RvpApiLodgingCqiService>(RvpApiLodgingCqiService);
      rvpApiLodgingCqiServiceStub.retrieve.resolves({ headers: {} });

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
          rvpApiLodgingCqiService: () => rvpApiLodgingCqiServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        rvpApiLodgingCqiServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(RvpApiLodgingCqi, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(rvpApiLodgingCqiServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.rvpApiLodgingCqis[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(RvpApiLodgingCqi, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(rvpApiLodgingCqiServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: RvpApiLodgingCqiComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RvpApiLodgingCqi, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        rvpApiLodgingCqiServiceStub.retrieve.reset();
        rvpApiLodgingCqiServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        rvpApiLodgingCqiServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(rvpApiLodgingCqiServiceStub.retrieve.called).toBeTruthy();
        expect(comp.rvpApiLodgingCqis[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(rvpApiLodgingCqiServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        rvpApiLodgingCqiServiceStub.retrieve.reset();
        rvpApiLodgingCqiServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(rvpApiLodgingCqiServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.rvpApiLodgingCqis[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(rvpApiLodgingCqiServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        rvpApiLodgingCqiServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRvpApiLodgingCqi();
        await comp.$nextTick(); // clear components

        // THEN
        expect(rvpApiLodgingCqiServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(rvpApiLodgingCqiServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
