<template>
  <div>
    <h2 id="page-heading" data-cy="RvpApiResponseHeading">
      <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.home.title')" id="rvp-api-response-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RvpApiResponseCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-rvp-api-response"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && rvpApiResponses && rvpApiResponses.length === 0">
      <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="rvpApiResponses && rvpApiResponses.length > 0">
      <table class="table table-striped" aria-describedby="rvpApiResponses">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('surveyId')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.surveyId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'surveyId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lodgingId')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.lodgingId')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lodgingId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('date')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.date')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'date'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('overallsatsifaction')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.overallsatsifaction')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'overallsatsifaction'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('customScore')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.customScore')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'customScore'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('plantorevisit')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.plantorevisit')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'plantorevisit'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('label')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.label')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'label'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rvpApiResponse in rvpApiResponses" :key="rvpApiResponse.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RvpApiResponseView', params: { rvpApiResponseId: rvpApiResponse.id } }">{{
                rvpApiResponse.id
              }}</router-link>
            </td>
            <td>{{ rvpApiResponse.surveyId }}</td>
            <td>{{ rvpApiResponse.lodgingId }}</td>
            <td>{{ formatDateShort(rvpApiResponse.date) || '' }}</td>
            <td>{{ rvpApiResponse.overallsatsifaction }}</td>
            <td>{{ rvpApiResponse.customScore }}</td>
            <td>{{ rvpApiResponse.plantorevisit }}</td>
            <td>{{ rvpApiResponse.label }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RvpApiResponseView', params: { rvpApiResponseId: rvpApiResponse.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RvpApiResponseEdit', params: { rvpApiResponseId: rvpApiResponse.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(rvpApiResponse)"
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
          id="reviewProStreamBurnerApplicationApp.rvpApiResponse.delete.question"
          data-cy="rvpApiResponseDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-rvpApiResponse-heading"
          v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiResponse.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-rvpApiResponse"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRvpApiResponse()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="rvpApiResponses && rvpApiResponses.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./rvp-api-response.component.ts"></script>
