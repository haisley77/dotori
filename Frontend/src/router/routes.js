const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
  },
  {
    path: '/my-page',
    component: () => import('layouts/MyPageLayout.vue'),
    children: [
      {
        path: 'info',
        component: () => import('pages/MyInfoPage.vue'),
      },
      {
        path: 'collection',
        component: () => import('pages/MyVideoCollectionPage.vue'),
      },
      {
        path: 'avatar',
        component: () => import('pages/MyAvatarPage.vue'),
      }
    ],
  },
];

export default routes;
