<template>
  <div>
    <h2 id="page-heading" data-cy="RvpApiGlobalReviewHeading">
      <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.home.title')" id="rvp-api-global-review-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RvpApiGlobalReviewCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-rvp-api-global-review"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && rvpApiGlobalReviews && rvpApiGlobalReviews.length === 0">
      <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="rvpApiGlobalReviews && rvpApiGlobalReviews.length > 0">
      <table class="table table-striped" aria-describedby="rvpApiGlobalReviews">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="t$('global.field.id')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lodgingid')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.lodgingid')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lodgingid'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('prevGri')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.prevGri')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'prevGri'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('distribution')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.distribution')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'distribution'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('gri')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.gri')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'gri'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fd')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.fd')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fd'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('td')">
              <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.td')"></span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'td'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rvpApiGlobalReview in rvpApiGlobalReviews" :key="rvpApiGlobalReview.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RvpApiGlobalReviewView', params: { rvpApiGlobalReviewId: rvpApiGlobalReview.id } }">{{
                rvpApiGlobalReview.id
              }}</router-link>
            </td>
            <td>{{ rvpApiGlobalReview.lodgingid }}</td>
            <td>{{ rvpApiGlobalReview.prevGri }}</td>
            <td>{{ rvpApiGlobalReview.distribution }}</td>
            <td>{{ rvpApiGlobalReview.gri }}</td>
            <td>{{ rvpApiGlobalReview.fd }}</td>
            <td>{{ rvpApiGlobalReview.td }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RvpApiGlobalReviewView', params: { rvpApiGlobalReviewId: rvpApiGlobalReview.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RvpApiGlobalReviewEdit', params: { rvpApiGlobalReviewId: rvpApiGlobalReview.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(rvpApiGlobalReview)"
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
          id="reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.delete.question"
          data-cy="rvpApiGlobalReviewDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-rvpApiGlobalReview-heading"
          v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiGlobalReview.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-rvpApiGlobalReview"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRvpApiGlobalReview()"
          ></button>
        </div>
      </template>
    </b-modal>
    <div v-show="rvpApiGlobalReviews && rvpApiGlobalReviews.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./rvp-api-global-review.component.ts"></script>
