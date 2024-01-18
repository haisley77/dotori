const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
  }, {
    path: '/room',
    component: () => import('pages/RoomPage.vue'),
  },
  {
    path: '/end',
    component: () => import('pages/EndPage.vue'),
  },
];

export default routes;
