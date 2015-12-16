    <body>
      <div class="jumbotron container-table">
        <div class="vertical-center-row ">
          <div class="row row-logo">
            <div class="col-md-6 col-md-offset-3">
              <center>
                <img src="${pageContext.servletContext.contextPath}/resources/img/logoA.png" class="img-responsive" alt="Responsive image">
                <h1 class="name-logo">L1nk</h1>
                <h3>Le r�seau d'anciens de Lille</h3>
              </center>
            </div>
          </div>  
          <div class="row container-bas">
            <div class="col-md-5 col-md-offset-3">
              <form class="form-horizontal">
                <div class="form-group">
                  <label for="inputEmail" class="col-md-4 control-label">Adresse email :</label>
                  <div class="input-group col-md-8">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></span>
                    <input type="email" class="form-control" id="inputEmail" placeholder="Email">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPassword" class="col-md-4 control-label">Mot de passe :</label>
                  <div class="input-group col-md-8">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
                    <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                  </div>
                </div>  
                <div class="row ">
                  <button type="submit" class="btn btn-default col-md-offset-2 col-md-5">S'inscrire</button>
                  <button type="submit" class="btn btn-default col-md-5">Connexion</button>
                </div>
              </form>
              <div class="row">
                <p class="col-md-offset-10 col-md-4">
                  <a class="small-link" href="#">Mot de pass� oubli� ?</a>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
      <script src="js/bootstrap.min.js"></script>
    </body>
