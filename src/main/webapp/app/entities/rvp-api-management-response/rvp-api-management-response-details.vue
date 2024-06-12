<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="rvpApiManagementResponse">
        <h2 class="jh-entity-heading" data-cy="rvpApiManagementResponseDetailsHeading">
          <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.detail.title')"></span>
          {{ rvpApiManagementResponse.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.source')"></span>
          </dt>
          <dd>
            <span>{{ rvpApiManagementResponse.source }}</span>
          </dd>
          <dt>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.lodgingId')"></span>
          </dt>
          <dd>
            <span>{{ rvpApiManagementResponse.lodgingId }}</span>
          </dd>
          <dt>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.fd')"></span>
          </dt>
          <dd>
            <span>{{ rvpApiManagementResponse.fd }}</span>
          </dd>
          <dt>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.td')"></span>
          </dt>
          <dd>
            <span>{{ rvpApiManagementResponse.td }}</span>
          </dd>
          <dt>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondableCountsPositive')"></span>
          </dt>
          <dd>
            <span>{{ rvpApiManagementResponse.respondableCountsPositive }}</span>
          </dd>
          <dt>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondableCountsNegative')"></span>
          </dt>
          <dd>
            <span>{{ rvpApiManagementResponse.respondableCountsNegative }}</span>
          </dd>
          <dt>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondedCountsPositive')"></span>
          </dt>
          <dd>
            <span>{{ rvpApiManagementResponse.respondedCountsPositive }}</span>
          </dd>
          <dt>
            <span v-text="t$('reviewProStreamBurnerApplicationApp.rvpApiManagementResponse.respondedCountsNegative')"></span>
          </dt>
          <dd>
            <span>{{ rvpApiManagementResponse.respondedCountsNegative }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="rvpApiManagementResponse.id"
          :to="{ name: 'RvpApiManagementResponseEdit', params: { rvpApiManagementResponseId: rvpApiManagementResponse.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./rvp-api-management-response-details.component.ts"></script>
