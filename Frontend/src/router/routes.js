const routes = [
    {
        path: '/',
        component: () => import('layouts/MainLayout.vue'),
    },
    {
        path: '/login',
        component: () => import('pages/LoginPage.vue'),
    },
    {
        path: '/signup',
        component: () => import('pages/SingupPage.vue'),
    },
];

export default routes;
