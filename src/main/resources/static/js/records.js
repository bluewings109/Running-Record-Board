function onClickDelete(recordId){
    if(confirm('정말로 삭제하시겠습니까?(ID : ' + recordId + ')')){
        //XMLHttpRequest 객체 생성
        const xhr = new XMLHttpRequest();

        //요청을 보낼 방식, 주소, 비동기여부 설정
        xhr.open('DELETE', '/records?recordId='+recordId, true);


        //요청 전송
        xhr.send();

        //통신후 작업
        xhr.onload = () => {
            //통신 성공
            if (xhr.status == 200) {
                alert(xhr.response);
            } else {
                //통신 실패
                alert('삭제 실패');
            }

            window.location.href='/records';
        }
    } else {
        alert('취소했습니다');
    }
}

