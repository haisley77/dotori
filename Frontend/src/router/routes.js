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
  }
]

export default routes
