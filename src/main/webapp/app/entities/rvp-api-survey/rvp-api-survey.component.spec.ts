/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RvpApiSurvey from './rvp-api-survey.vue';
import RvpApiSurveyService from './rvp-api-survey.service';
import AlertService from '@/shared/alert/alert.service';

type RvpApiSurveyComponentType = InstanceType<typeof RvpApiSurvey>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RvpApiSurvey Management Component', () => {
    let rvpApiSurveyServiceStub: SinonStubbedInstance<RvpApiSurveyService>;
    let mountOptions: MountingOptions<RvpApiSurveyComponentType>['global'];

    beforeEach(() => {
      rvpApiSurveyServiceStub = sinon.createStubInstance<RvpApiSurveyService>(RvpApiSurveyService);
      rvpApiSurveyServiceStub.retrieve.resolves({ headers: {} });

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
          rvpApiSurveyService: () => rvpApiSurveyServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        rvpApiSurveyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(RvpApiSurvey, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(rvpApiSurveyServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.rvpApiSurveys[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(RvpApiSurvey, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(rvpApiSurveyServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: RvpApiSurveyComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RvpApiSurvey, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        rvpApiSurveyServiceStub.retrieve.reset();
        rvpApiSurveyServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        rvpApiSurveyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(rvpApiSurveyServiceStub.retrieve.called).toBeTruthy();
        expect(comp.rvpApiSurveys[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(rvpApiSurveyServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        rvpApiSurveyServiceStub.retrieve.reset();
        rvpApiSurveyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(rvpApiSurveyServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.rvpApiSurveys[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(rvpApiSurveyServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        rvpApiSurveyServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeRvpApiSurvey();
        await comp.$nextTick(); // clear components

        // THEN
        expect(rvpApiSurveyServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(rvpApiSurveyServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
