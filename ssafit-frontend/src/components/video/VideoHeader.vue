<template>
  <hr style="margin: 0px;">
  <div class="row" style="background-color: rgb(170, 182, 250);font-size: large;"> 
      <div class="col-3"></div>
      <div class="container text-center py-3 row col-9">
        <div class="col-1">
          <RouterLink :to="{ name: 'VideoList' }" style="color: white;">전체영상</RouterLink>
        </div>
        <div class="col-1">
          <RouterLink :to="{ name: 'ChestList' }" style="color: white;">가슴</RouterLink>
        </div>
        <div class="col-1">
          <RouterLink :to="{ name: 'BackList' }" style="color: white;">등</RouterLink>
        </div>
        <div class="col-1">
          <RouterLink :to="{ name: 'AbsList' }" style="color: white;">복부</RouterLink>
        </div>
        <div class="col-1">
          <RouterLink :to="{ name: 'ShoulderList' }" style="color: white;">어깨</RouterLink>
        </div>
        <div class="col-1">
          <RouterLink :to="{ name: 'ArmList' }" style="color: white;">팔</RouterLink>
        </div>
        <div class="col-1">
          <RouterLink :to="{ name: 'LegList' }" style="color: white;">하체</RouterLink>
        </div>
        <div class="col-1">
          <RouterLink :to="{ name: 'Youtube' }" style="color: white;">영상검색</RouterLink>
        </div>
        <div class="col-4"></div>
      </div>
  <hr style="margin: 0px;">
  </div>
</template>

<script setup>

</script>

<style scoped>
a {
  text-decoration-line: none;
  color: black;
}
</style>