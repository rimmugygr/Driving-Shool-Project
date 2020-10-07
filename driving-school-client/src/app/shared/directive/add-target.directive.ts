import {Directive, Input, OnDestroy, OnInit, ViewContainerRef} from '@angular/core';
import {MenuService} from '../services/menu.service';

@Directive({
  selector: '[appAddTarget]'
})
export class AddTargetDirective implements OnInit{
  @Input('appAddTarget') targetName: string;

  constructor(private menuService: MenuService,
              private viewContainer: ViewContainerRef) { }

  ngOnInit(): void {
    this.menuService.addTarget(this.targetName, this.viewContainer);
  }

}
