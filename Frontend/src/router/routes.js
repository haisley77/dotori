const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
<<<<<<< HEAD
    children: [{ path: '', component: () => import('pages/IndexPage.vue') }],
  },
=======
  }
]
>>>>>>> acc2533784319c05176174d472c7282a77255895

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
