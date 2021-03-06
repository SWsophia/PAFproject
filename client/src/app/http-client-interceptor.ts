import {HttpHandler, HttpInterceptor, HttpRequest,HttpEvent} from "@angular/common/http";
import {LocalStorageService} from "ngx-webstorage";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

@Injectable()
export class HttpClientInterceptor implements HttpInterceptor {
  constructor(private $localStorage: LocalStorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = this.$localStorage.retrieve('authenticationToken')
    console.log(('jwt token: ' + token))
    if(token) {
      const cloned = req.clone( {
        headers: req.headers.set("Authorization", "Bearer " + token)
      })
      return next.handle(cloned)
      console.log("cloned",cloned)
    } else {
      return next.handle(req)
      console.log("request", req)
    }
  }
}
