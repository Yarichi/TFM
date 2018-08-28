import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

/*
  Generated class for the AuthInterceptorProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import { AuthProvider } from '../auth/auth';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthInterceptorProvider implements HttpInterceptor {
   constructor() {}

   intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       // Get the auth header from your auth service.
       var parts = req.url.split('/');
       if (parts[parts.length-1] != 'aa'){
        const authToken = localStorage.getItem('token');
        const he = req.headers.set('Authorization', `Bearer ${authToken}`);
        console.log(he);
        const authReq = req.clone({headers: he});
        return next.handle(authReq);
       }
       else return next.handle(req);
   }
}
