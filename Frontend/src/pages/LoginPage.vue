<template>
    <div class="bg-brown-3">
        <div
            class="q-pa-md row items-start q-gutter-md flex items-center justify-center"
        >
            <q-card class="my-card bg-white text-black rounded-borders">
                <q-card-section>
                    <div>
                        <div
                            id="text-container"
                            class="text-h6 flex items-center justify-center"
                        >
                            지금 도토리를 시작해 보세요
                        </div>
                        <div
                            id="text-container"
                            class="text-h4 flex items-center justify-center"
                        >
                            간편하게 SNS ID로 시작하기
                        </div>
                    </div>
                </q-card-section>

                <q-card-actions class="flex items-center justify-center">
                    <div class="select_btn-group">
                        <q-btn flat @click="klogin()">
                            <img src="../assets/login/login_btn_kakao.svg" />
                        </q-btn>
                        <q-btn flat @click="nlogin()">
                            <img src="../assets/login/login_btn_naver.svg" />
                        </q-btn>
                        <q-btn flat @click="glogin()">
                            <img src="../assets/login/login_btn_google.svg" />
                        </q-btn>
                    </div>
                </q-card-actions>
            </q-card>
        </div>
    </div>
</template>

<script setup>
    const klogin = () => {
        window.location.replace(
            'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&prompt=select_account',
        );
    };

    const nlogin = () => {
        window.location.replace(
            'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=jyvqXeaVOVmV&redirect_uri=http%3A%2F%2Fservice.redirect.url%2Fredirect&state=hLiDdL2uhPtsftcU',
        );
    };

    const glogin = () => {
        window.location.replace('https://accounts.google.com/o/oauth2/v2/auth');
    };
</script>

<style scoped>
    .rounded-borders {
        border-radius: 20px; /* 모서리를 조절할 값 */
    }
    #text-container {
        white-space: pre-line; /* 줄 바꿈을 강제로 지정합니다. */
    }
</style>
