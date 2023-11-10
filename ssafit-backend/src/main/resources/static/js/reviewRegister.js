var reviewAddBtn = document.querySelector(".review-submit-btn");
var reviewList = document.querySelector(".review-ul"); // Assuming this is the container for reviews

reviewAddBtn.addEventListener("click", addReview);

reviewList.addEventListener("click", function (event) {
    if (event.target.classList.contains("review-delete-btn")) {
        deleteReview(event);
    }
});

function addReview(event) {
    event.preventDefault();
    var reviewTxt = document.querySelector(".review-input-form").value;
    if (reviewTxt.trim() !== "") {
        var author = "익명";

        var newReview = document.createElement("li");
        newReview.className = "review-li";
        newReview.innerHTML = `
            <ul class="review-display">
                <li class="review-text">${reviewTxt}</li>
                <li class="review-author">${author}</li>
                <input type="button" value="삭제" class="review-delete-btn">
            </ul>
        `;

        reviewList.appendChild(newReview);

        document.querySelector(".review-input-form").value = "";
    } else {
        alert("리뷰를 작성해주세요.");
    }
}

function deleteReview(event) {
    event.preventDefault();

    var reviewElement = event.target.closest(".review-li");
    reviewElement.remove();
}


let video = {
    "id": "gMaB-fG4u4g",
    "title": "전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]",
    "part": "전신",
    "channelName": "ThankyouBUBU",
    "url": "https://www.youtube.com/embed/gMaB-fG4u4g"
}

const videoArea = document.querySelector(".video-area");

const videoHtml = `
        <content>
            <iframe width="560" height="315" src="${video.url}" title="${video.title}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
        </content>
    `;

    videoArea.insertAdjacentHTML("beforeend", videoHtml);