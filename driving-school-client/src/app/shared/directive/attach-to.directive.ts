import {Directive, Input, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {MenuService} from '../services/menu.service';

@Directive({
  selector: '[appAttachTo]'
})
export class AttachToDirective implements OnInit, OnDestroy {
  @Input('appAttachTo') targetName: string;

  constructor(private menuService: MenuService,
              private template: TemplateRef<any>) { }

  ngOnInit(): void {
    this.menuService.attach(this.targetName, this.template);
  }


  ngOnDestroy(): void {
    this.menuService.clearTarget(this.targetName);
  }


}
