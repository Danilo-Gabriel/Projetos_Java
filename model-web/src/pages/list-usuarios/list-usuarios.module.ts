import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListUsuariosComponent } from './list-usuarios/list-usuarios.component';
import { AppPrimeNGModule } from 'src/shared/app-primeNG/app-primeNG.module';
import { SharedModule } from 'src/shared/shared.module';



@NgModule({
  declarations: [
    ListUsuariosComponent
  ],
  imports: [
    CommonModule,
    AppPrimeNGModule,
    SharedModule
  ]
})
export class ListUsuariosModule { }
