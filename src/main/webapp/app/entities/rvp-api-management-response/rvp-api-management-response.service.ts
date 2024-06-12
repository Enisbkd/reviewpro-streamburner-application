import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { type IRvpApiManagementResponse } from '@/shared/model/rvp-api-management-response.model';

const baseApiUrl = 'api/rvp-api-management-responses';

export default class RvpApiManagementResponseService {
  public find(id: number): Promise<IRvpApiManagementResponse> {
    return new Promise<IRvpApiManagementResponse>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IRvpApiManagementResponse): Promise<IRvpApiManagementResponse> {
    return new Promise<IRvpApiManagementResponse>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IRvpApiManagementResponse): Promise<IRvpApiManagementResponse> {
    return new Promise<IRvpApiManagementResponse>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: IRvpApiManagementResponse): Promise<IRvpApiManagementResponse> {
    return new Promise<IRvpApiManagementResponse>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
