<template>
    <div>
        <div class="container text-center">
        <RouterLink :to="{ name: 'VideoList' }">전체영상</RouterLink> | 
        <RouterLink :to="{ name: 'ChestList' }">가슴</RouterLink> | 
        <RouterLink :to="{ name: 'BackList' }">등</RouterLink> | 
        <RouterLink :to="{ name: 'AbsList' }">복부</RouterLink> | 
        <RouterLink :to="{ name: 'ShoulderList' }">어깨</RouterLink> | 
        <RouterLink :to="{ name: 'ArmList' }">팔</RouterLink> | 
        <RouterLink :to="{ name: 'LegList' }">하체</RouterLink> |
        <RouterLink :to="{ name: 'Youtube' }">영상검색</RouterLink>
    </div>
    </div>
</template>

<script setup>

</script>

<style scoped>

</style>