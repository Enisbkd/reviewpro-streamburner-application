<template>
  <div>
    <h2 id="page-heading" data-cy="RvpApiManagementResponseHeading">
      <span
        v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.home.title')"
        id="rvp-api-management-response-heading"
      ></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RvpApiManagementResponseCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-rvp-api-management-response"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && rvpApiManagementResponses && rvpApiManagementResponses.length === 0">
      <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="rvpApiManagementResponses && rvpApiManagementResponses.length > 0">
      <table class="table table-striped" aria-describedby="rvpApiManagementResponses">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('source')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.source')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'source'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lodgingId')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.lodgingId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lodgingId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fd')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.fd')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fd'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('td')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.td')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'td'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('respondableCountsPositive')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondableCountsPositive')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'respondableCountsPositive'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('respondableCountsNegative')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondableCountsNegative')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'respondableCountsNegative'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('respondedCountsPositive')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondedCountsPositive')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'respondedCountsPositive'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('respondedCountsNegative')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondedCountsNegative')"></span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'respondedCountsNegative'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rvpApiManagementResponse in rvpApiManagementResponses" :key="rvpApiManagementResponse.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'RvpApiManagementResponseView', params: { rvpApiManagementResponseId: rvpApiManagementResponse.id } }"
                >{{ rvpApiManagementResponse.id }}</router-link
              >
            </td>
            <td>{{ rvpApiManagementResponse.source }}</td>
            <td>{{ rvpApiManagementResponse.lodgingId }}</td>
            <td>{{ rvpApiManagementResponse.fd }}</td>
            <td>{{ rvpApiManagementResponse.td }}</td>
            <td>{{ rvpApiManagementResponse.respondableCountsPositive }}</td>
            <td>{{ rvpApiManagementResponse.respondableCountsNegative }}</td>
            <td>{{ rvpApiManagementResponse.respondedCountsPositive }}</td>
            <td>{{ rvpApiManagementResponse.respondedCountsNegative }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RvpApiManagementResponseView', params: { rvpApiManagementResponseId: rvpApiManagementResponse.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RvpApiManagementResponseEdit', params: { rvpApiManagementResponseId: rvpApiManagementResponse.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(rvpApiManagementResponse)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.delete.question"
          data-cy="rvpApiManagementResponseDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-rvpApiManagementResponse-heading"
          v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-rvpApiManagementResponse"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRvpApiManagementResponse()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="rvpApiManagementResponses && rvpApiManagementResponses.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./rvp-api-management-response.component.ts"></script>
