<template>
    <div>
        <div class="q-pa-md">
            <q-btn color="teal" @click="showLoading" label="Show Loading" />
        </div>
    </div>
</template>

<script setup>
    import {useQuasar} from 'quasar';
    import {onBeforeUnmount} from 'vue';

    const quasar = useQuasar();
    let timer;

    onBeforeUnmount(() => {
        if (timer !== void 0) {
            clearTimeout(timer);
            if (quasar.loading) {
                quasar.loading.hide();
            }
        }
    });

    const showLoading = () => {
        if (quasar.loading) {
            quasar.loading.show({
                message: 'Some important process is in progress. Hang on...',
            });

            timer = setTimeout(() => {
                if (quasar.loading) {
                    quasar.loading.hide();
                }
                timer = void 0;
            }, 3000);
        }
    };
</script>

<style lang="scss" scoped></style>
