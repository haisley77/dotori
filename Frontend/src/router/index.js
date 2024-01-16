import { createRouter, createWebHistory } from 'vue-router';
import Roomlist from '../pages/Roomlist.vue';
import Booklist from '../pages/Booklist.vue';

const routes = [
  {
    path: '/',
    name: 'Roomlist',
    component: Roomlist
  },

  {
    path: '/Booklist',
    name: 'Booklist',
    component: Booklist,
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
