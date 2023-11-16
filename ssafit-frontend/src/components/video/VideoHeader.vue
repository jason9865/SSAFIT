<template>
    <div>
        <div class="container text-center">
        <RouterLink :to="{ name: 'ChestList' }">가슴</RouterLink> | 
        <RouterLink :to="{ name: 'BackList' }">등</RouterLink> | 
        <RouterLink :to="{ name: 'AbsList' }">복부</RouterLink> | 
        <RouterLink :to="{ name: 'ShoulderList' }">어깨</RouterLink> | 
        <RouterLink :to="{ name: 'ArmList' }">팔</RouterLink> | 
        <RouterLink :to="{ name: 'LegList' }">하체</RouterLink>
    </div>
    </div>
</template>

<script setup>

</script>

<style scoped>

</style>