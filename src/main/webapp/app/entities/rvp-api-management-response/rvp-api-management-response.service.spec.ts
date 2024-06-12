/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import RvpApiManagementResponseService from './rvp-api-management-response.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { RvpApiManagementResponse } from '@/shared/model/rvp-api-management-response.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('RvpApiManagementResponse Service', () => {
    let service: RvpApiManagementResponseService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new RvpApiManagementResponseService();
      currentDate = new Date();
      elemDefault = new RvpApiManagementResponse(123, 'AAAAAAA', 0, currentDate, currentDate, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fd: dayjs(currentDate).format(DATE_FORMAT),
            td: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a RvpApiManagementResponse', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            fd: dayjs(currentDate).format(DATE_FORMAT),
            td: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            fd: currentDate,
            td: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a RvpApiManagementResponse', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a RvpApiManagementResponse', async () => {
        const returnedFromService = Object.assign(
          {
            source: 'BBBBBB',
            lodgingId: 1,
            fd: dayjs(currentDate).format(DATE_FORMAT),
            td: dayjs(currentDate).format(DATE_FORMAT),
            respondableCountsPositive: 1,
            respondableCountsNegative: 1,
            respondedCountsPositive: 1,
            respondedCountsNegative: 1,
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            fd: currentDate,
            td: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a RvpApiManagementResponse', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a RvpApiManagementResponse', async () => {
        const patchObject = Object.assign(
          {
            respondableCountsPositive: 1,
            respondableCountsNegative: 1,
            respondedCountsNegative: 1,
          },
          new RvpApiManagementResponse(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            fd: currentDate,
            td: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a RvpApiManagementResponse', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of RvpApiManagementResponse', async () => {
        const returnedFromService = Object.assign(
          {
            source: 'BBBBBB',
            lodgingId: 1,
            fd: dayjs(currentDate).format(DATE_FORMAT),
            td: dayjs(currentDate).format(DATE_FORMAT),
            respondableCountsPositive: 1,
            respondableCountsNegative: 1,
            respondedCountsPositive: 1,
            respondedCountsNegative: 1,
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            fd: currentDate,
            td: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of RvpApiManagementResponse', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a RvpApiManagementResponse', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a RvpApiManagementResponse', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
