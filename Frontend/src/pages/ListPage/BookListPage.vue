<template>
  <headerbooklist/>
  <q-page class="page-container">
    <div class='row q-col-gutter-x-md q-col-gutter-y-md'>
      <div v-for='book in books' :key='book.bookId' class='col-12 col-sm-6 col-md-4 col-lg-3 q-pa-lg'>
        <Book :bookdetail="book"></Book>
      </div>
    </div>
  </q-page>
</template>

<script>
  import {onMounted, ref} from 'vue';
  import Book from 'components/ListPageComponents/Book.vue';
  import {localAxios} from 'src/axios/http-commons';
  import Headerbooklist from 'components/CommonComponents/Headerbooklist.vue';

  const axios = localAxios();
  export default {
    components: {Headerbooklist, Book},
    setup() {
      const books = ref([]);

      onMounted(() => {
        fetchBooks();
      });

      const fetchBooks = async () => {
        try {
          const response = await axios.get('/api/books',{withCredentials: true});
          console.log('API Response:', response);
          books.value = response.data.books;
        } catch (error) {
          console.error('Error fetching books:', error);
        }
      };

      return {
        books,
        dialog: ref(false),
        maximizedToggle: ref(true),
      };
    },
  };
</script>

<style lang='scss' scoped>
  .page-container {
    margin-top: 80px;
    margin-left : 210px;
    width : 1500px;
  }

</style>
