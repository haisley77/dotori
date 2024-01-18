const routes = [
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
  {
    path: '/room',
    component: () => import('pages/RoomPage.vue'),
  },
  {
    path: '/end',
    component: () => import('pages/EndPage.vue'),
  },
];

export default routes;
