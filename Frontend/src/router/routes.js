const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
  }, {
    path: '/room',
    component: () => import('pages/RoomPage.vue'),
  },
];

export default routes;
