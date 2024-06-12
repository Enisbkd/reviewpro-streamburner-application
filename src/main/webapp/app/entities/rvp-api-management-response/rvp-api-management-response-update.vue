<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.home.createOrEditLabel"
          data-cy="RvpApiManagementResponseCreateUpdateHeading"
          v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="rvpApiManagementResponse.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="rvpApiManagementResponse.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.source')"
              for="rvp-api-management-response-source"
            ></label>
            <input
              type="text"
              class="form-control"
              name="source"
              id="rvp-api-management-response-source"
              data-cy="source"
              :class="{ valid: !v$.source.$invalid, invalid: v$.source.$invalid }"
              v-model="v$.source.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.lodgingId')"
              for="rvp-api-management-response-lodgingId"
            ></label>
            <input
              type="number"
              class="form-control"
              name="lodgingId"
              id="rvp-api-management-response-lodgingId"
              data-cy="lodgingId"
              :class="{ valid: !v$.lodgingId.$invalid, invalid: v$.lodgingId.$invalid }"
              v-model.number="v$.lodgingId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.fd')"
              for="rvp-api-management-response-fd"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="rvp-api-management-response-fd"
                  v-model="v$.fd.$model"
                  name="fd"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="rvp-api-management-response-fd"
                data-cy="fd"
                type="text"
                class="form-control"
                name="fd"
                :class="{ valid: !v$.fd.$invalid, invalid: v$.fd.$invalid }"
                v-model="v$.fd.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.td')"
              for="rvp-api-management-response-td"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="rvp-api-management-response-td"
                  v-model="v$.td.$model"
                  name="td"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="rvp-api-management-response-td"
                data-cy="td"
                type="text"
                class="form-control"
                name="td"
                :class="{ valid: !v$.td.$invalid, invalid: v$.td.$invalid }"
                v-model="v$.td.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondableCountsPositive')"
              for="rvp-api-management-response-respondableCountsPositive"
            ></label>
            <input
              type="number"
              class="form-control"
              name="respondableCountsPositive"
              id="rvp-api-management-response-respondableCountsPositive"
              data-cy="respondableCountsPositive"
              :class="{ valid: !v$.respondableCountsPositive.$invalid, invalid: v$.respondableCountsPositive.$invalid }"
              v-model.number="v$.respondableCountsPositive.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondableCountsNegative')"
              for="rvp-api-management-response-respondableCountsNegative"
            ></label>
            <input
              type="number"
              class="form-control"
              name="respondableCountsNegative"
              id="rvp-api-management-response-respondableCountsNegative"
              data-cy="respondableCountsNegative"
              :class="{ valid: !v$.respondableCountsNegative.$invalid, invalid: v$.respondableCountsNegative.$invalid }"
              v-model.number="v$.respondableCountsNegative.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondedCountsPositive')"
              for="rvp-api-management-response-respondedCountsPositive"
            ></label>
            <input
              type="number"
              class="form-control"
              name="respondedCountsPositive"
              id="rvp-api-management-response-respondedCountsPositive"
              data-cy="respondedCountsPositive"
              :class="{ valid: !v$.respondedCountsPositive.$invalid, invalid: v$.respondedCountsPositive.$invalid }"
              v-model.number="v$.respondedCountsPositive.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondedCountsNegative')"
              for="rvp-api-management-response-respondedCountsNegative"
            ></label>
            <input
              type="number"
              class="form-control"
              name="respondedCountsNegative"
              id="rvp-api-management-response-respondedCountsNegative"
              data-cy="respondedCountsNegative"
              :class="{ valid: !v$.respondedCountsNegative.$invalid, invalid: v$.respondedCountsNegative.$invalid }"
              v-model.number="v$.respondedCountsNegative.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./rvp-api-management-response-update.component.ts"></script>
