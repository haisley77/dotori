const routes = [
    {
        path: '/',
        component: () => import('layouts/MainLayout.vue'),
        // children: [
        //     {
        //         path: '',
        //         component: () => import('pages/HomePage.vue'),
        //     },
        //     {
        //         path: 'login',
        //         component: () => import('pages/LoginPage.vue'),
        //     },
        // ],
    },
    {
        path: '/login',
        component: () => import('pages/LoginPage.vue'),
    },
];

export default routes;
