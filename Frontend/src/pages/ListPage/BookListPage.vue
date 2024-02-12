<template>
  <q-page padding>

    <div class='row q-mb-md'>

      <div class='search row flex justify-center items-center' style='width: 100%;'>
        <q-input
          class=''
          standout rounded dense placeholder='검색'
          color='black' bg-color='white'
          style='width: 50%; border: #C7A96E solid 5px; border-radius: 50px; '
        >
        </q-input>
        <q-btn
          class=''
          icon='search'
          round
          size='lg'
          flat
          unelevated
          style='color: #C7A96E; font-weight: bolder'
        />
      </div>
    </div>

    <div class='row q-col-gutter-x-md q-col-gutter-y-md'>
      <div v-for='book in books' :key='book.bookId' class='col-12 col-sm-6 col-md-4 col-lg-3 q-pa-md'>
        <div
          class="book-container"
          @mouseover="showModal(book)"
          @mouseleave="hideModal"
        >
          <Book class='book-component' :bookdetail="book"></Book>
            <!-- 모달 내용 -->
            <q-card class="my-card modal" v-if="selectedBook === book">
              <img :src="book.bookImg">
              <q-card-section>
                <div class="text-h6">연극을 하려면 {{ book.roleCnt }} 명의 친구들이 필요해요!</div>
              </q-card-section>
              <q-card-section class="q-pt-none">
                시작 버튼을 누르고 방을 만들어볼까요?
              </q-card-section>
            </q-card>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
  import { onMounted, ref } from 'vue';
  import Book from 'components/ListPageComponents/Book.vue';
  import { localAxios } from 'src/axios/http-commons';

  const axios = localAxios();
  export default {
    components: { Book },
    setup() {
      const books = ref([]);
      const selectedBook = ref(null);

      onMounted(() => {
        fetchBooks();
      });

      const fetchBooks = async () => {
        try {
          const response = await axios.get('/api/books', { withCredentials: true });
          console.log('API Response:', response);
          books.value = response.data.books;
        } catch (error) {
          console.error('Error fetching books:', error);
        }
      };

      const showModal = (book) => {
        selectedBook.value = book;
      };

      const hideModal = () => {
        selectedBook.value = null;
      };

      return {
        books,
        selectedBook,
        showModal,
        hideModal,
      };
    },
  };
</script>

<style lang='scss' scoped>
  .book-container {
    position: relative;

  }
  .book-container:hover {
    cursor: pointer;
  }
  .book-component:hover {
    transform: scale(1.1); /* 1.1 배 확대 */
    transition: transform 0.3s ease;
  }
  .modal {
    position: absolute;
    z-index: 9999;
    top: calc(10% + 10px);
    left: calc(100% + 20px);
    width: 300px; /* 모달의 너비 */
    height: 350px; /* 모달의 높이 */
    background-color: white;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 30px; /* 테두리를 둥글게 만듭니다. */
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }
</style>
