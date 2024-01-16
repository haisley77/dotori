const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
  },
  {
    path: '/room-recording',
    component: () => import('pages/RoomRecording.vue'),
  }
]

export default routes
