<template>
  <headerbooklist/>
  <q-page class="page-container">
    <div class='row q-col-gutter-x-md q-col-gutter-y-md'>
      <div v-for='(book,index) in books' :key='book.bookId' class='col-3 q-pa-md'>
        <div
          class="book-container"
          @mouseover="showModal(book)"
          @mouseleave="hideModal"
        >
          <Book class='book-component' :bookdetail="book"></Book>
            <!-- 모달 내용 -->
            <q-card class="my-card " :class="{'modal-left':index===3, 'modal-right':index!==3}" v-if="selectedBook === book">
              <img :src="book.bookImg">
              <q-card-section>
                <div class="text-h6 npsfont">연극을 하려면 {{ book.roleCnt }} 명의 친구들이 필요해요!</div>
              </q-card-section>
              <q-card-section class="q-pt-none npsfont">
                '방만들기' 버튼을 눌러 친구들을 모아볼까요?
              </q-card-section>
            </q-card>
        </div>
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
        dialog: ref(false),
        maximizedToggle: ref(true),
        selectedBook,
        showModal,
        hideModal,
      };
    },
  };
</script>

<style lang='scss' scoped>
  .page-container {
    margin-top: 80px;
    margin-left : 50px;

    width : 1500px;
  }
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
  .modal-right {
    position: absolute;
    z-index: 9999;
    top: calc(5% + 10px);
    left: calc(80% + 20px);
    width: 300px; /* 모달의 너비 */
    height: 360px; /* 모달의 높이 */
    background-color: white;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 30px; /* 테두리를 둥글게 만듭니다. */
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }
  .modal-left {
      position: absolute;
      z-index: 9999;
      top: calc(10% + 10px);
      left: calc(-100% + 20px);
      width: 300px; /* 모달의 너비 */
      height: 350px; /* 모달의 높이 */
      background-color: white;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 30px; /* 테두리를 둥글게 만듭니다. */
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }
</style>
