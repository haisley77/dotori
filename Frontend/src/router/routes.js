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
  {
    path: '/room-recording',
    component: () => import('pages/RoomRecording.vue'),
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
    path: '/room-recording-v2',
    component: () => import('pages/RoomRecordingV2.vue'),
  },
  {
    path: '/room-recording-v3',
    component: () => import('pages/RoomRecordingV3.vue'),
  }
];

export default routes

