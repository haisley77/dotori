const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
  },
  {
    path: '/room-recording',
    component: () => import('pages/RoomRecording.vue'),
  },
  {
    path: '/room-recording-v2',
    component: () => import('pages/RoomRecordingV2.vue'),
  },
  {
    path: '/room-recording-v3',
    component: () => import('pages/RoomRecordingV3.vue'),
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
