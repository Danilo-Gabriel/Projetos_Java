import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environment/environment';
import { AppMessageService } from 'src/shared/components-services/app-message/app-message.service';
import { Usuario } from '../../dto/DadosUsuario';
import { Location } from '@angular/common';
import { EditUsuario } from '../../dto/DadosAtualizarLogin';
import { window } from 'rxjs';
import { LocalStorageService } from 'src/shared/components-services/services/localStorage/localStorage.service';

@Injectable({
  providedIn: 'root'
})
export class EditUsuarioService {

  constructor(

    private http : HttpClient,
    private message : AppMessageService,
    private router : Router,
    private location : Location,


    ) {


  }

  private backendURL = environment.endPoint;
  private readonly API = `${this.backendURL}/usuario/atualizar`
  private readonly buscarDadosUsuario = `${this.backendURL}/usuario`

  atualizarLoginUser(record: EditUsuario ){

    return this.http.put<Usuario>(this.API, record, {responseType: 'json'})
    .subscribe(
      (response) => {

      this.message.showSuccess("Usuário alterado com sucesso");
      this.location.back()

    },
    (error) => {
       this.message.showError(error.error)


    }
  );
  }

  buscarDadosUser(id : string){

    return this.http.get<Usuario>(`${this.buscarDadosUsuario}/${id}`)
  }


  }
