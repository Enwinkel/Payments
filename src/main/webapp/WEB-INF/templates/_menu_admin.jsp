<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        Payments
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="controller?action=users"><fmt:message key="main.menu.button.subscribers"/>
                    <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="controller?action=requests"><fmt:message key="main.menu.button.requests"/>
                    <c:if test="${usersReq.size() > 0}">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                            ${usersReq.size()}
                        </span>
                    </c:if>
                    <span class="sr-only">(current)</span>
                </a>
            </li>
        </ul>
        <div class="dropdown">
            <button class="btn btn-outline-secondary btn-sm" type="button" id="dropdownMenuButton"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="material-icons">
                    language
                </i>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <form class="form-inline" method="post" action="controller?action=localization">
                    <button type="submit" name="ua" class="dropdown-item">Ukrainian</button>
                    <button type="submit" name="en" class="dropdown-item">English</button>
                </form>
            </div>
        </div>
        <form class="form-inline my-2 my-lg-0" method="post" action="controller?action=logout">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message
                    key="mainmenu.button.logout"/></button>
        </form>
    </div>
</nav>
