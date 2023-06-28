import React from 'react'
import {Link} from "react-router-dom";

function PublicPage() {

  return (
      <>
        <div> Página Inicial </div>
        <Link to={"/login"}> Faça seu Login </Link>
      </>
  )
}

export default PublicPage;
