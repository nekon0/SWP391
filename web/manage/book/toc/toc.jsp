<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-lg-4 col-xlg-3 col-md-4">
    <div class="card">
        <div class="card-body profile-card">
            <h4 class="card-title mt-2"> 
                <a target="_blank" href="../../BookDetail?id=${book.getId()}"><i class="fa fa-external-link-alt" aria-hidden="true"></i></a>
                    ${book.title}
                <a href="./AddVolume?id=${book.id}"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
                <a href="./ArrangeVolume?id=${book.id}"><i class="fa fa-random" aria-hidden="true"></i></a>
            </h4>

            <c:forEach items="${requestScope.volumes}" var="volume">
                <h5  class="card-subtitle">
                    <a id="vol${volume.id}" href="./TOC?id=${book.id}&vid=${volume.id}">${volume.volumeName}</a>
                    <a href="./AddChapter?id=${book.id}&vid=${volume.id}"><i class="fa fa-plus-circle" aria-hidden="true"></i></a>
                    <a href="./ArrangeChapter?id=${volume.id}"><i class="fa fa-random" aria-hidden="true"></i></a>
                </h5>
                <h5 class="card-subtitle">
                    <ul class="list-group list-group-flush">
                        <c:forEach items="${requestScope.chapters}" var="chapter">
                            <c:if test="${chapter.volumeID==volume.id}">
                                <li class="list-group-item">
                                    <a target="_blank" href="#"><i class="fa fa-external-link-alt" aria-hidden="true"></i></a>
                                    <a href="./TOC?id=${book.id}&cid=${chapter.id}">   ${chapter.chapterName}</a>
                                </li>
                            </c:if>

                        </c:forEach>
                    </ul>
                </h5>
            </c:forEach>

        </div>
    </div>
</div>