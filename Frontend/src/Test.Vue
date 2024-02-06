<template>
  <!--  <!DOCTYPE html>-->
  <!--  <html lang="ko">-->
  <!--  <head>-->
  <!--    <meta charset="UTF-8">-->
  <!--    <title>Title</title>-->
  <!--  </head>-->
  <!--  <body>-->
  <div>
    <input id="video-file" type="file" name="file">
    <button @click="sendVideoChunks">업로드</button>
    <div id="result"></div>
  </div>
  <!--  </body>-->
  <!--  </html>-->
</template>

<script setup>
  const sendVideoChunks = () => {
    const chunkSize = 1024 * 1024; // 1MB
    const file = document.getElementById('video-file').files[0];
    const resultElement = document.getElementById('result');

    // total size 계산
    const totalChunks = Math.ceil(file.size / chunkSize);
    let currentChunk = 0;

    // chunk file 전송
    const sendNextChunk = () => {

      // chunkFile size 만큼 데이터 분할
      const start = currentChunk * chunkSize;
      const end = Math.min(start + chunkSize, file.size);

      const chunkFile = file.slice(start, end);
      console.log('######################chunk start~#####################');
      console.log('start', start);
      console.log('end', end);
      console.log('chunkFile', chunkFile);

      // form data 형식으로 전송
      const formData = new FormData();
      formData.append('fileName', file.name);
      formData.append('chunkFile', chunkFile, file.name);
      formData.append('chunkNumber', currentChunk);
      formData.append('isEnd', totalChunks <= currentChunk + 1);
      console.log('fileName', file.name);
      console.log('chunkNumber', currentChunk);
      console.log('isEnd', totalChunks <= currentChunk + 1);
      console.log('totalChunk', totalChunks);
      console.log('currentChunk', currentChunk);

      fetch('http://localhost:8080/api/v1/videos', {
        method: 'POST',
        body: formData,
      }).then(resp => {
        // 전송 결과가 206이면 다음 파일 조각 전송
        if (resp.status === 206) {
          // 진행률 표시
          resultElement.textContent = Math.round(currentChunk / totalChunks * 100) + '%';
          currentChunk++;
          if (currentChunk < totalChunks) {
            sendNextChunk();
          }
          // 마지막 파일까지 전송 되면
        } else if (resp.status === 200) {
          console.log('######################~chunk end~#####################');
          resp.text().then(data => resultElement.textContent = data);
        }
      }).catch(err => {
        console.error('Error uploading video chunkFile');
      });
    };

    sendNextChunk();
  };
</script>
<style lang="scss" scoped>

</style>
