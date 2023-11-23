<template>
  <div>
    <SlidingImage/>

    <div>
        <HomeBoard/>
    </div>

  </div>
</template>

<script setup>
import SlidingImage from '@/components/common/SlidingImage.vue'
import HomeBoard from '@/components/common/HomeBoard.vue'

// const sessionData = JSON.parse(window.localStorage.getItem("loginUser"))?._value;

</script>

<style scoped>

</style>