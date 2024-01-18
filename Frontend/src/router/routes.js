const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/IndexPage.vue')},
      { path: 'intro', component: () => import('pages/Intro.vue')},
  ],
  },
  {
    path: '/List',
    component: () => import('layouts/List/Index.vue'),
    children: [
      { path: '', component: () => import('pages/List/IndexPage.vue')},
  ],
  },
  {
    path: '/book',
    component: () => import('layouts/List/Index.vue'),
    children: [
      { path: '', component: () => import('pages/List/BookIndexPage.vue')},
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
