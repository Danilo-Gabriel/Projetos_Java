
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from 'src/pages/home/home.component';
import { ListUsuariosComponent } from './list-usuarios/list-usuarios.component';


const routes: Routes = [

  {path:'', component:LoginComponent},

  {path:'home/list', component: ListUsuariosComponent},

  {path: 'home', component:HomeComponent}


]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
