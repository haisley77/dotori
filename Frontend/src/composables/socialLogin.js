import {reactive, toRefs} from 'vue';
import {useRoute} from 'vue-router';
import {useCookies} from '@vueuse/integrations/useCookies';
import {useAxios} from 'vue-axios';

export default function useSocialLogin() {
    const route = useRoute();
    const cookies = useCookies();
    const {axios} = useAxios();

    const state = reactive({
        loginResult: {
            status: '',
        },
    });

    const doSocialLogin = async socialType => {
        const apiPath = '/user/social-login'; //api만들면 변경
        const bodyData = {
            code: route.query.code,
            userType: socialType,
        };

        try {
            const response = await axios.post(apiPath, bodyData);
            state.loginResult.status = 'SUCCESS';
            cookies.set('user-key', response.data.id);
            window.opener.location.replace('/');
            window.close();
        } catch (error) {
            state.loginResult.status = 'FAIL';
            console.error('error', error);
        }
    };

    return {...toRefs(state), doSocialLogin};
}
