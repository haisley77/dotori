const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/IndexPage.vue')},
      { path: 'breakpoints', component: () => import('pages/Breakpoints.vue')},
  ],
  },
  {
    path: '/List',
    component: () => import('layouts/List/Index.vue'),
    children: [
      { path: '', component: () => import('pages/List/IndexPage.vue')},
  ],
  },


  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
