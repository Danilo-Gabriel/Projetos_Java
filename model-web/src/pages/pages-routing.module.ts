import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ListUsuarioComponent } from './usuario/list-usuario/list-usuario.component';
import { AddUsuarioComponent } from './usuario/add-usuario/add-usuario.component';
import { EditUsuarioComponent } from './usuario/edit-usuario/edit-usuario.component';
import { MainComponent } from './main/main.component';
import { RecuperarSenhaComponent } from './login/recuperar-senha/recuperar-senha.component';
import { AuthGuard } from 'src/app/guard/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate : [AuthGuard],
    children: [
      { path: 'home',
       component: MainComponent,
       canActivate : [AuthGuard]},
      { path: 'usuario',
       component: ListUsuarioComponent,
       canActivate : [AuthGuard]},
      { path: 'usuario/new',
       component: AddUsuarioComponent,
       canActivate : [AuthGuard]},
      { path: 'usuario/edit/:idUsuario',
       component: EditUsuarioComponent,
       canActivate : [AuthGuard], }

    ]
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
